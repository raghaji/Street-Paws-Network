import React from 'react';
import { Link, useNavigate  } from 'react-router-dom';

function  Navbar() {
    const navigate = useNavigate();
    const isAuthenticated = !!localStorage.getItem('accessToken');
    const handleLogout = () => {
        localStorage.removeItem('user');
        localStorage.removeItem('accessToken');
        navigate('/login');
      };
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
                    <Link to="/Signup" style={styles.link}>Register</Link>
                </li>
                {isAuthenticated ? (
                    <>
                        <li style={styles.navItem}><Link to="/create-post">Create Post</Link></li>
                        <li style={styles.navItem}><button onClick={handleLogout}>Logout</button></li>
                    </>
                    ) : (
                    <>
                    <li style={styles.navItem}>
                        <Link to="/login" style={styles.link}>Login</Link>
                    </li>
                </>
                )}

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
