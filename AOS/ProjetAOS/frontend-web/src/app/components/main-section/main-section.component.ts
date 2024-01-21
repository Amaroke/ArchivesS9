import { Component, OnInit } from '@angular/core';
import { VehiculeService } from '../../shared/services/vehicule.service';
import { Vehicule } from '../../shared/types/vehicule.type';

@Component({
  selector: 'app-main-section',
  templateUrl: './main-section.component.html',
  styleUrls: [],
})
export class MainSectionComponent implements OnInit {
  private _vehicules: Vehicule[];
  private _showCreatePopup = false;
  private _showLoginPopup = false;
  vehiculeSelected: Vehicule;
  searchQuery: string;
  searchDate: Date | undefined;
  searchOwner: boolean;

  constructor(private vehiculesService: VehiculeService) {
    this._vehicules = [] as Vehicule[];
    this.vehiculeSelected = {} as Vehicule;
    this._showCreatePopup = false;
    this._showLoginPopup = false;
    this.searchQuery = '';
    this.searchDate = undefined;
    this.searchOwner = false;
  }

  ngOnInit(): void {
    this.vehiculesService.getVehicules().subscribe((vehicules) => {
      this._vehicules = vehicules;
    });
  }

  get vehicules(): Vehicule[] {
    return this._vehicules;
  }

  get showCreatePopup(): boolean {
    return this._showCreatePopup;
  }

  get showLoginPopup(): boolean {
    return this._showLoginPopup;
  }

  openCreatePopup(): void {
    this._showCreatePopup = true;
  }

  closeCreatePopup(): void {
    this._showCreatePopup = false;
  }

  openLoginPopup(): void {
    this._showLoginPopup = true;
  }

  closeLoginPopup(): void {
    this._showLoginPopup = false;
  }
}
