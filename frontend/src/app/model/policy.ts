export interface Policy {
  id: string;
  type: 'AUTO' | 'HOME' | 'HEALTH' | 'LIFE';
  description: string;
  price: number;
  contractDate: string;
};