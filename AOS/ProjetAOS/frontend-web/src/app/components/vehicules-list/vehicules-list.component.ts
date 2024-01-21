import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Vehicule } from '../../shared/types/vehicule.type';

@Component({
  selector: 'app-vehicules-list',
  templateUrl: './vehicules-list.component.html',
  styleUrls: [],
})
export class VehiculesListComponent {
  @Input() vehicules: Vehicule[];
  @Input() searchQuery: string;
  @Input() searchDate: Date | undefined;
  @Input() searchOwner: boolean;
  @Output() searchOwnerChange = new EventEmitter<boolean>();
  @Input() vehiculeSelected: Vehicule;
  @Output() vehiculeSelectedChange = new EventEmitter<Vehicule>();

  constructor() {
    this.vehicules = [];
    this.vehiculeSelected = {} as Vehicule;
    this.searchQuery = '';
    this.searchDate = undefined;
    this.searchOwner = false;
  }

  toggleSelection(vehicule: Vehicule): void {
    if (this.isSelected(vehicule)) {
      this.vehiculeSelected = {} as Vehicule;
      this.vehiculeSelectedChange.emit(this.vehiculeSelected);
    } else {
      this.vehiculeSelected = vehicule;
      this.vehiculeSelectedChange.emit(this.vehiculeSelected);
    }
  }

  isSelected(vehicule: Vehicule): boolean {
    return this.vehiculeSelected == vehicule;
  }
}
