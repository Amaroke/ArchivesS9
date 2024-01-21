import { Component, EventEmitter, Input, Output } from '@angular/core';
import { VehiculeService } from '../../shared/services/vehicule.service';
import { Vehicule } from '../../shared/types/vehicule.type';

@Component({
  selector: 'app-vehicule-create-popup',
  templateUrl: './vehicule-create-popup.component.html',
  styleUrls: [],
})
export class VehiculeCreatePopupComponent {
  vehicule: Vehicule;
  @Input() vehicules: Vehicule[];
  @Output() close = new EventEmitter<void>();

  constructor(private _vehiculeService: VehiculeService) {
    this.vehicules = [];
    this.vehicule = {
      immatriculation: '',
      type: '',
      marque: '',
      modele: '',
      categorie: '',
      boiteDeVitesse: '',
      nbDePlaces: 0,
      description: '',
    };
  }

  saveVehicule() {
    this._vehiculeService.addVehicule(this.vehicule).subscribe(() => {
      this.closePopup();
      window.location.reload();
    });
  }

  closePopup(): void {
    this.close.emit();
  }
}
