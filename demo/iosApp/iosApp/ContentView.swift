import SwiftUI
import shared

struct ContentView: View {
	let greet = Greeting().greeting()
    let movie = Movie(name: "asd")
    

	var body: some View {
        MoviesListScreenView()
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
	ContentView()
	}
}
