import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { CrudSectionComponent } from './components/crud-section/crud-section.component';
import { MainSectionComponent } from './components/main-section/main-section.component';
import { NavigationBarComponent } from './components/navigation-bar/navigation-bar.component';
import { SearchSectionComponent } from './components/search-section/search-section.component';
import { VehiculeCreatePopupComponent } from './components/vechicule-create-popup/vehicule-create-popup.component';
import { VehiculesListComponent } from './components/vehicules-list/vehicules-list.component';
import { LoginPopupComponent } from './components/login-popup/login-popup.component';

@NgModule({
  declarations: [
    AppComponent,
    CrudSectionComponent,
    MainSectionComponent,
    NavigationBarComponent,
    SearchSectionComponent,
    VehiculeCreatePopupComponent,
    VehiculesListComponent,
    LoginPopupComponent,
  ],
  imports: [BrowserModule, HttpClientModule, FormsModule],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
