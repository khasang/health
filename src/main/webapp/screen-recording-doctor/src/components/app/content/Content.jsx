import React from 'react';
import s from './Content.module.css'
import ContentSpeciality from "./contentSpeciality/ContentSpeciality";
import {BrowserRouter, Route} from "react-router-dom";
import ContentDepartment from "./contentDepartment/ContentDepartment";

const Content = (props) => {
    let departments = props.state.mapDepartment.map(department =>
        <Route exact path={'/' + department.speciality.split('(').join('-').split(')').join('-')}
               render={() => <ContentSpeciality state={department}/>}/>
    );

    return (
        <BrowserRouter>
            <div className={props.className}>
                <div className={s.main}>
                    <div>
                        <Route exact path='/' render={() => <ContentDepartment state={props.state.mapDepartment}/>}/>
                        <div>
                            {departments}
                        </div>
                    </div>
                </div>
            </div>
        </BrowserRouter>
    )
};

export default Content;