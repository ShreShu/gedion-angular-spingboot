import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-save-video-details',
  templateUrl: './save-video-details.component.html',
  styleUrls: ['./save-video-details.component.css']
})
export class SaveVideoDetailsComponent implements OnInit {

  SaveDetailForm:FormGroup;



  constructor(private _fb:FormBuilder) { 

    this.SaveDetailForm=this._fb.group({
      title:['',Validators.required],
      description:['',Validators.required],
      video_status:['']
    })

  }

  ngOnInit(): void {





  }

  SaveDetails(){}

}
