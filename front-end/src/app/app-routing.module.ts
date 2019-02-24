import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { IndexComponent } from './pages/index/index.component';
import { GameComponent } from './pages/game/game.component';
import { AuthGuard } from './guards/auth.guard';
import { ResultsComponent } from './pages/results/results.component';

const routes: Routes = [
  {
    path: '',
    component: IndexComponent
  },
  {
    path: 'game',
    component: GameComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'results',
    component: ResultsComponent,
    canActivate: [AuthGuard]
  }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule { }
