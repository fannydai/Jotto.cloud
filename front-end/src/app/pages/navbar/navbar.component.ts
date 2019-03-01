import { Component, OnInit, ViewChild, TemplateRef } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  @ViewChild('confirm') modalContent: TemplateRef<any>;

  constructor(
    private user: UserService,
    private router: Router,
    private modal: NgbModal,
  ) { }

  ngOnInit() {
  }

  /**
   * Navigates to index page.
   */
  navigateHome(): void {
    if (this.router.url === '/game') {
      this.modal.open(this.modalContent, { centered: true }).result.then((result) => {
        if (result === 'Leave') {
          this.router.navigate(['/']);
        }
      });
    } else {
      this.router.navigate(['/']);
    }
  }

  /**
   * Navigate to game page if not already there.
   */
  navigateGame(): void {
    if (this.router.url !== '/game') {
      this.router.navigate(['/game']);
    }
  }

  /**
   * Navigate to results page if not already there. Prompts users if they are in a game.
   */
  navigateResult(): void {
    if (this.router.url !== '/results') {
      if (this.router.url === '/game') {
        // Confirmation modal if in middle of game
        this.modal.open(this.modalContent, { centered: true }).result.then((result) => {
          if (result === 'Leave') {
            this.router.navigate(['/results']);
          }
        });
      } else {
        this.router.navigate(['/results']); // If user is not in game
      }
    }
  }

  /**
   * Logs out user and navigates to index page. Prompts users if they are in a game.
   */
  navigateLogout(): void {
    if (this.router.url === '/game') {
      this.modal.open(this.modalContent, { centered: true }).result.then((result) => {
        if (result === 'Leave') {
          this.user.logout();
          this.router.navigate(['/']);
        }
      });
    } else {
      this.user.logout();
      this.router.navigate(['/']);
    }
  }

}
