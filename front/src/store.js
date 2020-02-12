import { createStore, applyMiddleware } from "redux";
import logger from 'redux-logger';

import rootReducer from './redusers';

export default () => {
    return createStore( rootReducer, applyMiddleware(logger) );
};
