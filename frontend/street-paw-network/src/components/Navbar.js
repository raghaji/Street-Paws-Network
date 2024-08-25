import React from 'react';
import { Link } from 'react-router-dom';

const Navbar = () => {
    return (
        <nav style={styles.navbar}>
            <div style={styles.logo}>
                <Link to="/" style={styles.link}>Street Paw Network</Link>
            </div>
            <ul style={styles.navLinks}>
                <li style={styles.navItem}>
                    <Link to="/home" style={styles.link}>Home</Link>
                </li>
                <li style={styles.navItem}>
                    <Link to="/login" style={styles.link}>Login</Link>
                </li>
                <li style={styles.navItem}>
                    <Link to="/register" style={styles.link}>Register</Link>
                </li>
            </ul>
        </nav>
    );
};

const styles = {
    navbar: {
        display: 'flex',
        justifyContent: 'space-between',
        alignItems: 'center',
        backgroundColor: '#333',
        padding: '10px',
        color: 'white'
    },
    logo: {
        fontSize: '20px',
        fontWeight: 'bold',
    },
    navLinks: {
        listStyle: 'none',
        display: 'flex',
        margin: 0,
        padding: 0,
    },
    navItem: {
        marginLeft: '20px',
    },
    link: {
        color: 'white',
        textDecoration: 'none',
    }
};

export default Navbar;
