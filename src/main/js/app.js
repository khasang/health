import React from 'react';
import * as ReactDOM from "react-dom";
import {createStore} from 'devextreme-aspnet-data-nojquery';
import {Item} from 'devextreme-react/form';

import {
    Column,
    DataGrid,
    Editing,
    FilterRow,
    Form,
    Pager,
    Paging,
    Popup,
    Position
} from 'devextreme-react/data-grid';


const url = '/horse';

const dataSource = createStore({
    key: 'id',
    loadUrl: `${url}/all`,
    insertUrl: `${url}/add`,
    updateUrl: `${url}/update`,
    deleteUrl: `${url}/delete`,
    onBeforeSend: (method, ajaxOptions) => {
        ajaxOptions.xhrFields = {withCredentials: true};
    }
});

class App extends React.Component {

    render() {
        if ({dataSource}) {
            return (
                <div>
                    <DataGrid
                        elementAttr={{
                            id: 'gridContainer'
                        }}
                        dataSource={dataSource}
                        showBorders={true}
                        remoteOperations={true}
                        wordWrapEnabled={true}
                    >
                        <FilterRow visible={true} applyFilter="auto"/>
                        <Editing
                            mode={'popup'}
                            allowAdding={true}
                            allowUpdating={true}
                            allowDeleting={true}>
                            <Popup title={'Horse Info'} showTitle={true} width={700} height={525}>
                                <Position my={'top'} at={'top'} of={window}/>
                            </Popup>
                            <Form>
                                <Item itemType={'group'} colCount={2} colSpan={2}>
                                    <Item dataField={'name'}/>
                                    <Item dataField={'description'}/>
                                </Item>
                            </Form>
                        </Editing>
                        <Paging defaultPageSize={10}/>
                        <Pager
                            showInfo={true}/>
                        <Column dataField={'name'}/>
                        <Column dataField={'description'}/>
                    </DataGrid>
                </div>
            );
        } else {
            return null;
        }
    }
}

ReactDOM.render(
    <App/>,
    document.getElementById('react')
);
