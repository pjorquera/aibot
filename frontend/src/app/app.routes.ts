import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: 'home',
    loadComponent: () => import('./pages/home/home.page').then((m) => m.HomePage),
  },
  {
    path: 'detail/:id',
    loadComponent: () =>
      import('./pages/detail/detail.page').then((m) => m.DetailPage),
  },
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full',
  },
];

