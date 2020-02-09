import React, {useState} from 'react';
import Button from "react-bootstrap/Button";
import {createNewBank} from "./actions";
import Form from "react-bootstrap/Form";
import Card from "react-bootstrap/Card";
import './Banks.css';

export default () => {
    const [banks, setBanks] = useState([]);
    const [bank, setBank] = useState({name: ''});

    const createBank = async (e) => {
        e.preventDefault();
        bank.name && setBanks([
            ...banks,
            await createNewBank(bank)
        ])
    };

    const handleChange = (e) => {
        setBank({
            name: e.target.value
        })
    };

    return (
        <div><h3>Banks</h3>
            <Form>
                <Form.Group controlId="formBanks">
                    <Form.Label>Банк</Form.Label>
                    <Form.Control
                        type="text"
                        placeholder="Введите название банка"
                        onChange={handleChange}/>
                </Form.Group>
                <Button
                    variant='success'
                    onClick={createBank}
                    type='submit'
                >Создать банк</Button>
            </Form>
            <div className="Cards" >
                {banks && banks.map(({bankId, name}) => <Card key={bankId} style={{ width: '18rem' }} border={"success"}>
                    <Card.Body>
                        <Card.Title>{name}</Card.Title>
                        <Card.Subtitle className="mb-2 text-muted">Идентефикатор: {bankId}</Card.Subtitle>
                        <Card.Text>
                            Какое-то описание банка
                        </Card.Text>
                    </Card.Body>
                </Card>)}
            </div>
        </div>
    )
}