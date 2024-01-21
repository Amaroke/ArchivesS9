import { Component, EventEmitter, Input, Output } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login-popup',
  templateUrl: './login-popup.component.html',
})
export class LoginPopupComponent {
  numeroMembre: string = '';
  motDePasse: string = '';

  @Output() close = new EventEmitter<void>();

  constructor(private http: HttpClient) {}

  login() {
    const employe = {
      numeroMembre: this.numeroMembre,
      motDePasse: this.motDePasse,
    };
    console.log('Connexion en cours:', employe);
    this.http.put('/api/json/employes/login', employe).subscribe(
      // Si la réponse est true
      (response) => {
        console.log('Connexion réussie:', response);
        localStorage.setItem('numeroMembre', this.numeroMembre);
        this.closePopup();
      },
      // Si la réponse est false
      (error) => {
        console.log('Connexion échouée:', error);
        alert('Connexion échouée');
      }
    );
  }

  closePopup(): void {
    this.close.emit();
  }
}
