import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Router, RouterStateSnapshot } from '@angular/router';
import { UserService } from './user.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGaurdService {
  logout() {
    throw new Error('Method not implemented.');
  }

  constructor(private router: Router,
    private authService: UserService) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    if (this.authService.isUserLoggedIn())
      return true;
    this.router.navigate(['signin']);
    return false;
  }

}