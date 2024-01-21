import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Vehicule } from '../../shared/types/vehicule.type';
import { VehiculeService } from '../../shared/services/vehicule.service';

@Component({
  selector: 'app-crud-section',
  templateUrl: './crud-section.component.html',
  styleUrls: [],
})
export class CrudSectionComponent {
  @Input() vehiculeSelected: Vehicule;
  @Input() vehicules: Vehicule[];
  @Output() openCreate = new EventEmitter<void>();
  @Output() openLogin = new EventEmitter<void>();
  @Output() openPopupSurvey = new EventEmitter<void>();

  constructor(private _vehiculeService: VehiculeService) {
    this.vehiculeSelected = {} as Vehicule;
    this.vehicules = [];
  }

  deleteVehicule(): void {
    if (this.vehiculeSelected.immatriculation) {
      this._vehiculeService.deleteVehicule(
        this.vehiculeSelected.immatriculation
      );
    }
  }

  deconnexion(): void {
    localStorage.removeItem('numeroMembre');
  }

  isLoggedOut(): boolean {
    return !localStorage.getItem('numeroMembre');
  }

  openPopup(): void {
    this.openCreate.emit();
  }

  openLoginPopup(): void {
    this.openLogin.emit();
  }

  openSurvey(): void {
    this.openPopupSurvey.emit();
  }
}
