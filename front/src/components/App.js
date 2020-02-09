import React, {PureComponent} from 'react';
import {Container} from "react-bootstrap";
import 'bootstrap/dist/css/bootstrap.min.css';
import Banks from './Banks';

class App extends PureComponent {

    constructor(props) {
        super(props);
        this.state = {
            banks: {}
        }
    }

   render() {
        const { banks } = this.state;
       return <div className="App">
           <Container>
               <Banks banks={banks} />
           </Container>
       </div>
   }


}

export default App;
