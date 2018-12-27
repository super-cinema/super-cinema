import { Injectable, NgModule} from '@angular/core';
import { HttpEvent, HttpInterceptor, HttpHandler, HttpRequest, HttpResponse} from '@angular/common/http';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable()
export class HttpsRequestInterceptor implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    console.log('headers');
    const dupReq = req.clone({ headers: req.headers.set('Access-Control-Allow-Origin', '*'), });
    const dupReq2 = dupReq.clone({ headers: req.headers.set('Access-Control-Allow-Methods', '*'), });
    const dupReq3 = dupReq2.clone({ headers: req.headers.set('Access-Control-Allow-Credentials', 'true'), });
    return next.handle(dupReq3);
  }
}
@NgModule({
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: HttpsRequestInterceptor, multi: true }
  ]
})
export class InterceptorModule { }
