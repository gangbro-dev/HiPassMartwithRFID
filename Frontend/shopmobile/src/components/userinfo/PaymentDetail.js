import React from 'react';

const PaymentDetail = () => {
  return (
    <table>
      <thead>
        <tr>
          <th>Product Name</th>
          <th>Quantity</th>
          <th>Unit Price</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>Product 1</td>
          <td>10</td>
          <td>$50</td>
        </tr>
        <tr>
          <td>Product 2</td>
          <td>5</td>
          <td>$100</td>
        </tr>
      </tbody>
    </table>
  );
};

export default PaymentDetail;
