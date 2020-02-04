import React, {PureComponent} from 'react';
import {Container} from "react-bootstrap";
import createBank from "../api/apiFetch";
import Button from "react-bootstrap/Button";
import 'bootstrap/dist/css/bootstrap.min.css';

class App extends PureComponent {

    constructor(props) {
        super(props);
        this.state = {
            bank: {}
        }
    }

   render() {
        const { bank : { bankId } } = this.state;
       return <div className="App">
           <Container>
               <Button
                   variant='success'
                    onClick={() => createBank().then( bank => this.setState({ bank }))}
               >Создать банк</Button>
               {bankId}
           </Container>
       </div>
   }


}

export default App;
