import React from 'react';

const PaymentDetail = () => {
  const products = [    { name: 'Product 1', quantity: 10, unitPrice: 50, numberic: true },    { name: 'Product 2', quantity: 5, unitPrice: 100 },  ];

  const totalAmount = products.reduce((total, product) => {
    return total + product.quantity * product.unitPrice;
  }, 0);

  return (
    <>
      <div style={{ textAlign: 'right', padding: '10px' }}>
        Total Purchase Amount: ${totalAmount}
      </div>
      <table style={{ width: '100%', borderCollapse: 'collapse' }}>
        <thead>
          <tr style={{ borderBottom: '1px solid #ddd', textAlign: 'left' }}>
            <th style={{ padding: '10px' }}>Product Name</th>
            <th style={{ padding: '10px' }}>Quantity</th>
            <th style={{ padding: '10px' }}>Unit Price</th>
          </tr>
        </thead>
        <tbody>
          {products.map((product, index) => (
            <tr key={index} style={{ borderBottom: '1px solid #ddd', textAlign:product.numberic || false ? "left" : "right" }}>
              <td style={{ padding: '10px' }}>{product.name}</td>
              <td style={{ padding: '10px' }}>{product.quantity}</td>
              <td style={{ padding: '10px' }}>${product.unitPrice}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </>
  );
};

export default PaymentDetail;
