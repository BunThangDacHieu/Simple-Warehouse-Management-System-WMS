import { ApplicationConfig, provideZoneChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';
import { providePrimeNG } from 'primeng/config';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import Aura from '@primeuix/themes/aura';
import { MessageService } from 'primeng/api';
import { routes } from './app.routes';
import {
  HTTP_INTERCEPTORS,
  provideHttpClient,
  withInterceptorsFromDi,
  withInterceptors,
} from '@angular/common/http';
import { ErrorHandler } from './core/interceptors/ErrorHandler';
import { apiKeyInterceptor } from './core/interceptors/api-key.interceptor';
export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(routes),
    MessageService,
    provideHttpClient(
      withInterceptorsFromDi(),
      withInterceptors([apiKeyInterceptor])
    ),
    {
      provide: HTTP_INTERCEPTORS,
      useClass: ErrorHandler,
      multi: true,
    },
    providePrimeNG({
      theme: {
        preset: Aura,
      },
    }),
    provideAnimationsAsync(),
    MessageService,
  ],
};
