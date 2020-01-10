import React from 'react';
import s from './Header.module.css'

const Header = (props) => {
    return (
        <header className={`${s.app_wrapper}`}>
            <div className={s.image}>
                <img src={props.state.imgSrc} alt={props.state.imgLogo}/>
            </div>
            <div className={s.title}>
                Запись к доктору
            </div>
        </header>
    )
};

export default Header;