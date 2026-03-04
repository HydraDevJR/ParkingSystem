import React from 'react'
import Header from './Header'
import SideBar from './SideBar'
import Main from './Main'
import Footer from './Footer'

const Layout = ({ children }) => (
	<div className="layout">
		<Header />
		<div className="layout-body">
			<SideBar />
			<Main>{children}</Main>
		</div>
		<Footer />
	</div>
)

export default Layout

