import dva from 'dva';
import './index.css';

// 1. Initialize
const app = dva({
    // history: createHistory({
    //     basename:'react'
    // })
});
// 2. Plugins
// app.use({});

// 3. Model
// app.model(require('./models/addp').default);
app.model(require('./models').app);
app.model(require('./models').Good);

// 4. Router
app.router(require('./routers').default);

// 5. Start
app.start('#root');
