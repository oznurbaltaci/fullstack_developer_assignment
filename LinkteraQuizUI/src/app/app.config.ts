import {environment} from '../environments/environment';

// let TOKEN = 'Bearer ';
export const AUTH_URL = environment.baseApiUrl + 'api/auth';
export const USER_URL = environment.baseApiUrl + 'api/user';
export const BOOK_URL = environment.baseApiUrl + 'api/book';
export const RENT_URL = environment.baseApiUrl + 'api/rent';
export const RENTED_HISTORY_BOOK_URL = environment.baseApiUrl + 'api/rent/history';
export const CURRENT_RENTED_BOOK_URL = environment.baseApiUrl + 'api/rent/current';
export const USER_RENTED_BOOK_HISTORY_URL = environment.baseApiUrl + 'api/rent/historyUser';
export const USER_RENTED_CURRENT_BOOK_URL = environment.baseApiUrl + 'api/rent/rentedUser';
export const RENTED_BOOK_HISTORY_URL = environment.baseApiUrl + 'api/rent/historyBook';
export const BOOK_RENTED_CURRENT_URL = environment.baseApiUrl + 'api/rent/rentedBook';
