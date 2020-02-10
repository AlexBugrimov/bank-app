import React from 'react';
import {Container} from "react-bootstrap";
import 'bootstrap/dist/css/bootstrap.min.css';
import Banks from './Banks';

export default () => {

    return <div className="App">
        <Container>
            <Banks/>
        </Container>
    </div>
}
