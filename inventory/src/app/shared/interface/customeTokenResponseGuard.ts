import {jwtDecode} from 'jwt-decode';

export interface CustomeTokenResponseGuard{
  sub: string,
  roles: {authority: string}[],
  exp: number,
  iat: number
}



