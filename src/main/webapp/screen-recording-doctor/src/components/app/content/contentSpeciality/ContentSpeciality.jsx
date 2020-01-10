import React from 'react';
import s from './ContentSpeciality.module.css';
import {NavLink} from "react-router-dom";

const ContentSpeciality = (props) => {
        let doctors = props.state.mapNameDoctor.map(department =>
            <div key={department.id} className={s.item}>
                <NavLink key={department.id} to={'/'}>{department.nameDoctor}</NavLink>
            </div>
        );

        return (
            <div className={props.className}>
                <div>
                    <div className={s.selectedSpecialityGrid}>
                        <div className={`${s.item} ${s.back}`}>
                            <NavLink to={'/'}>{'Назад'}</NavLink>
                        </div>
                        <div className={s.outerTitleSpeciality}>
                            <h3>{props.state.speciality[0].toUpperCase() + props.state.speciality.substring(1)}</h3>
                        </div>
                    </div>

                    <div className={s.wrapperDoctors}>
                        {doctors}
                    </div>
                </div>
            </div>
        )
    }
;

export default ContentSpeciality;