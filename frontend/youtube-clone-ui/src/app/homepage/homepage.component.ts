import { Component, OnInit } from '@angular/core';
import { Video } from '../model/video';
import { VideoService } from '../video.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {

AllVideos:Video[]= new Array<Video>();


  constructor(private _video:VideoService,private _router:Router) { }

  ngOnInit(): void {
this._video.getAllVideos().subscribe(data=>{
  this.AllVideos=data
console.log(this.AllVideos);
    })
  }



  playVideo(id:string){
    this._router.navigate(['/play-video'],{queryParams:{v:id}})
  }
}
