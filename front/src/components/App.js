import React, {PureComponent} from 'react';
import {Container} from "react-bootstrap";
import 'bootstrap/dist/css/bootstrap.min.css';
import Banks from './Banks';

class App extends PureComponent {

    constructor(props) {
        super(props);
    }

   render() {
       return <div className="App">
           <Container>
               <Banks/>
           </Container>
       </div>
   }


}

export default App;
