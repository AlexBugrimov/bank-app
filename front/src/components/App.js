import React from 'react';
import {Container} from "react-bootstrap";
import 'bootstrap/dist/css/bootstrap.min.css';
import Banks from './Banks';
import Header from "./Header";

export default () => {

    return <div className="App">
        <Header/>
        <Container>
            <Banks/>
        </Container>
    </div>
}
