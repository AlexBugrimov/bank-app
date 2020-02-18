import React from "react";
import {render} from "react-dom";
import App from "./components/App";
import {Provider} from 'react-redux';

import createStore from "./store";
const store = createStore();

render(
    <Provider store={store}>
        <App/>
    </Provider>,
    document.getElementById('root')
);