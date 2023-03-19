import React from 'react';
import {NavLink as ReactLink} from "react-router-dom"
//import PropTypes from 'prop-types';
import {
  Collapse,
  Navbar,
  NavbarToggler,
  NavbarBrand,
  Nav,
  NavItem,
  NavLink,
  UncontrolledDropdown,
  DropdownToggle,
  DropdownMenu,
  DropdownItem } from 'reactstrap';

  const NavbarProps = {
    light: true,
    dark: false,
    fixed: null,
    color: 'light',
    role: 'navigation',
    expand: false,
    tag: 'nav',
 
  };

export default class Example extends React.Component {
  constructor(props) {
    super(props);

    this.toggle = this.toggle.bind(this);
    this.state = {
      isOpen: false
    };
  }
  toggle() {
    this.setState({
      isOpen: !this.state.isOpen
    });
  }
  render() {
    return (
      <div>
         <Navbar {...NavbarProps}>
        <Navbar color="light" light expand="md">
          <NavbarBrand tag={ReactLink} to="/">Blogging Web Application</NavbarBrand>
          <NavbarToggler onClick={this.toggle} />
          <Collapse isOpen={this.state.isOpen} navbar>
            <Nav className="ml-auto" navbar>
              <NavItem>
                <NavLink tag={ReactLink} to="/About/">About</NavLink>
              </NavItem>
              <NavItem>
                <NavLink tag={ReactLink} to="/Login">Login</NavLink>
              </NavItem>
              <NavItem>
                <NavLink tag={ReactLink} to="/Signup">Signup</NavLink>
              </NavItem>
              <UncontrolledDropdown nav inNavbar>
                <DropdownToggle nav caret>
                  More
                </DropdownToggle>
                <DropdownMenu right>
                  <DropdownItem tag={ReactLink} to="/service">
                    Service
                  </DropdownItem>
                  <DropdownItem>
                     Facebook
                  </DropdownItem>
                  <DropdownItem divider />
                  <DropdownItem>
                    Reset
                  </DropdownItem>
                </DropdownMenu>
              </UncontrolledDropdown>
            </Nav>
          </Collapse>
        </Navbar></Navbar>
      </div>
    );
  }
}