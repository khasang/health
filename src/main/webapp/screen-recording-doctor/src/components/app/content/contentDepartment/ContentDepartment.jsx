import React from 'react';
import s from './ContentDepartment.module.css';
import {NavLink} from "react-router-dom";

const ContentDepartment = (props) => {
    /*
    При поступлении строки далее которая будет ссулкой на некий источник запрещены символы '(' ')'
    данные символы будут преобразованы в символ '-'
     */
    let departments = props.state.map(state =>
        <div key={state.id} className={s.item}>
            <NavLink
                to={state.speciality.split('(').join('-').split(')').join('-')}>
                {state.speciality[0].toUpperCase() + state.speciality.substring(1)}
            </NavLink>
        </div>
    );

    return (
        <div className={props.className}>
            <div className={s.wrapperDoctors}>
                {departments}
            </div>
        </div>
    )
};

export default ContentDepartment;