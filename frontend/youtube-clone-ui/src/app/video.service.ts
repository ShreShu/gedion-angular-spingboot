import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FileSystemFileEntry } from 'ngx-file-drop';
import { Observable } from 'rxjs';
import { Video } from './model/video';
import { uploadVideoResponse } from './upload-video/uploadVideoResponse';

@Injectable({
  providedIn: 'root'
})
export class VideoService {

  constructor(private _httpClient:HttpClient) { }

uploadVideo(fileEntry:File):Observable<uploadVideoResponse>{

  const formData = new FormData()
  formData.append('file', fileEntry, fileEntry.name)

   // Headers
  const headers = new HttpHeaders({
              'security-token': 'mytoken'
  })
  

  //Http post call
  return this._httpClient.post<uploadVideoResponse>("http://localhost:9091/api/videos/upload",formData);
}


getAllVideos():Observable<Video[]>{
  return this._httpClient.get<Video[]>("http://localhost:9091/api/videos/allvideos");
}


getVideoById(id:string){
  return this._httpClient.get("http://localhost:9091/api/videos/video/"+id)
}

}
