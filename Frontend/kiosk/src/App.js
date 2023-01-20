import React from 'react';
import {
  BrowserRouter,
  Routes,
  Route,
} from "react-router-dom";
import Home from './components/Home';
import ResultPayment from './components/ResultPayment';
import ItemList from './components/ItemList';


function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="resultpayment" element={<ResultPayment />} />
        <Route path="itemlist" element={<ItemList />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
