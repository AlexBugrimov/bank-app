import React from "react";
import {render} from "react-dom";
import App from "./components/App";
import {Provider} from 'react-redux';

import createStore from "./store";
const store = createStore();

setTimeout(function () {
    store.dispatch({
        type: 'SET_BANKS',
        payload: [
            {
                id: 0,
                name: 'VTB'
            }
        ]
    })
}, 1000);

render(
    <Provider store={store}>
        <App/>
    </Provider>,
    document.getElementById('root')
);