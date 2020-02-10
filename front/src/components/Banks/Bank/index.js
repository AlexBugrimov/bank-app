import React from 'react';
import Card from "react-bootstrap/Card";
import './Bank.css';

export default ( { bankId, name } ) => {
    return (
        <Card key={bankId} className="Card" style={{width: '18rem'}}
              border={"success"}>
            <Card.Body>
                <Card.Title className="Card__title">{name}</Card.Title>
                <Card.Subtitle className="mb-2 text-muted">Идентефикатор: {bankId}</Card.Subtitle>
            </Card.Body>
        </Card>
    );
}