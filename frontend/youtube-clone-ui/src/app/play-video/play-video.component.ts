import { Component, OnInit } from '@angular/core';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { ActivatedRoute } from '@angular/router';
import { Video } from '../model/video';
import { VideoService } from '../video.service';

@Component({
  selector: 'app-play-video',
  templateUrl: './play-video.component.html',
  styleUrls: ['./play-video.component.css']
})
export class PlayVideoComponent implements OnInit {

  id:string
  video:Video;
  errorMessage:string
  loading:boolean
  videourltodisplay:SafeResourceUrl
  urls;
  constructor(private _activatedRoute:ActivatedRoute,private _videoservice:VideoService,private _domsanitizer:DomSanitizer) { }

  ngOnInit() {

    this.id=this._activatedRoute.snapshot.queryParamMap.get('v')
    console.log(this.id)
    this._videoservice.getVideoById(this.id).subscribe((data:Video)=>{
      this.video=data
      this.videourltodisplay=this._domsanitizer.bypassSecurityTrustResourceUrl(this.video.videourl)
      
      console.log(this.video.videourl)
    },
    (error) => {                              //error() callback
      console.error('Request failed with error')
      this.errorMessage = error;
      this.loading = false;
    },
    () => {                                   //complete() callback
      console.error('Request completed')      //This is actually not needed 
      this.loading = false; 
    })
  }

}
