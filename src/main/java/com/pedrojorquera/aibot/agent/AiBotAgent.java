package com.pedrojorquera.aibot.agent;

import com.pedrojorquera.aibot.model.Policy;
import com.pedrojorquera.aibot.repository.PolicyRepository;
import dev.langchain4j.agent.tool.Tool;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.spring.AiService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Configuration
class Config {

    @Bean
    ChatMemory aiBotChatMemory() {
        return MessageWindowChatMemory.withMaxMessages(30);
    }

    @Bean
    ApplicationRunner applicationRunner(AiBotAgent aiBotAgent) {

        System.out.println(
                """
                        ███╗   ███╗ █████╗ ██████╗ ███████╗██████╗ ███████╗
                        ████╗ ████║██╔══██╗██╔══██╗██╔════╝██╔══██╗██╔════╝
                        ██╔████╔██║███████║██████╔╝█████╗  ██████╔╝█████╗
                        ██║╚██╔╝██║██╔══██║██╔═══╝ ██╔══╝  ██╔══██╗██╔══╝
                        ██║ ╚═╝ ██║██║  ██║██║     ██║     ██║  ██║███████╗
                        ╚═╝     ╚═╝╚═╝  ╚═╝╚═╝     ╚═╝     ╚═╝  ╚═╝╚══════╝

                """);

        return args -> {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("Cliente: ");
                String userMessage = scanner.nextLine();
                if ("exit".equalsIgnoreCase(userMessage)) {
                    break;
                }
                System.out.println();
                System.out.println("MAPFRE: " + aiBotAgent.chat(userMessage));
                System.out.println();
            }
            scanner.close();
        };
    }

}

@Component
class AiBotTools {

    private final PolicyRepository policyRepository;

    AiBotTools(PolicyRepository todoRepository) {
        this.policyRepository = todoRepository;
    }

    @Tool("Create policy and retrieve its ID")
    String createTodoAndRetrieveID(Policy.Type type, String description, double price) {
        return policyRepository.save(new Policy(type, description, price, new Date())).getId();
    }

    @Tool("Cancel policy")
    void cancelPolicy(String id) {
        policyRepository.deleteById(id);
    }

    @Tool("List policies")
    List<Policy> listPolicies() {
        return StreamSupport.stream(policyRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Tool("List policies contracted in the last 180 seconds")
    List<Policy> listPoliciesContractedInTheLast180Seconds() {
        long currentTimeMillis = System.currentTimeMillis();
        Date endDate = new Date(currentTimeMillis);
        Date startDate = new Date(currentTimeMillis - 180 * 1000);
        return policyRepository.findByContractDateBetween(startDate, endDate);
    }

    @Tool("Get price in euros for policy type")
    double getPriceForPolicyType(Policy.Type type) {
        return switch (type) {
            case AUTO -> 100.0;
            case HOME -> 150.0;
            case HEALTH -> 50.0;
            case LIFE -> 200.0;
        };
    }

    @Tool("Apply 5% discount to policy")
    void applyDiscountToPolicy(String id) {
        policyRepository.save(policyRepository.findById(id).get().applyDiscount());
    }

    @Tool("Get current time")
    Date getCurrentTime() {
        return new Date();
    }

}

@AiService(tools = "aiBotTools", chatMemory = "aiBotChatMemory")
public interface AiBotAgent {

    @SystemMessage({
            "You are an agent of MAPFRE, an insurance company located in Spain.",
            "Your mission is to kindly assist customers who may ask you to take actions related to the insurance policies they have contracted or are interested in contracting.",
            "Always try to ensure that if someone wants to cancel, they can think a little more about it taking into account the advantages of being a customer at MAPFRE",
            "In any case, if they insist, listen to them and cancel the policies they want.",
            "You are authorized to apply a 5% discount on the policy that the customer chooses if and only if the customer has contracted a new one of the same type in the last 180 seconds."
    })
    String chat(String input);
}
