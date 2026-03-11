import React from 'react'
import Header from './header/Header'
import SideBar from './side-bar/SideBar'
import Main from './main-layout/MainLayout'
import Footer from './footer/Footer'

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

