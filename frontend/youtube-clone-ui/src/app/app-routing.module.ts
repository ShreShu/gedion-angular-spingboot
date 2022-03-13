import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SaveVideoDetailsComponent } from './save-video-details/save-video-details.component';
import { UploadVideoComponent } from './upload-video/upload-video.component';
import {  HomepageComponent} from './homepage/homepage.component';
import { PlayVideoComponent } from './play-video/play-video.component';

const routes: Routes = [
  {    path:"upload-vedio", component:UploadVideoComponent },
  {    path:"save-video-details/:videoId", component:SaveVideoDetailsComponent},
  {    path:"home",component:HomepageComponent},
  {    path:"play-video", component:PlayVideoComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
