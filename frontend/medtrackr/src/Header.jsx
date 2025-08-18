import React from 'react';
import './App.css'; // external styles
import './styles/Header.css'

const Header = ({ children, greeting }) => {

  return (
    <header className="site-header">
      <h1>{greeting}</h1>
      <div>{children}</div>
    </header>
  );
};

export default Header;
