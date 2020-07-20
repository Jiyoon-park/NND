

import Login from './views/user/Login.vue'
import Join from './views/user/Join.vue'
import FeedMain from './views/feed/IndexFeed.vue'
import PageNotFound from './views/er/PageNotFound.vue'
import Components from './views/Components.vue'
import Findpw from './views/user/Findpw.vue'

export default [


    {
        path : '/',
        name : 'Login',
        component : Login
    },
    {
        path : '/user/join',
        name : 'Join',
        component : Join
    },
    {
        path : '/feed/main',
        name : 'FeedMain',
        component : FeedMain
    },
    {
        path : '*',
        redirect: '/404'
    },
    {
        path : '/404',
        name : 'PageNotFound',
        component : PageNotFound
    },
    {
        path : '/components',
        name : 'Components',
        component : Components
    },
    {
        path : '/user/findpw',
        name : 'Findpw',
        component : Findpw
    }
]
