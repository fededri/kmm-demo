//
//  MoviesListScreenView.swift
//  iosApp
//
//  Created by Federico Torres on 10/07/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct MoviesListScreenView: View {
    let api: TMDApi = TMDApi(threadInfo: ThreadInfoImpl())
    
    @State var movies: [Movie] = []
    
    var body: some View {
        NavigationView {
            List {
                ForEach(movies, id: \.self) { movie in
                    MovieView(movie: movie)
                }
            }
            .navigationTitle("Movies")
            .toolbar(content: {
                HStack {
                    Button("asd") {
                        
                    }
                    Button("Load movies") {
                            api.getMovies(type: .popular) { movies, error in
                                guard error == nil,
                                      let movies = movies else  {
                                    print(error!.localizedDescription)
                                    return
                                }
                                self.movies = movies
                            }
                    }
                }
        })
        }
    }
}

struct MoviesListScreenView_Previews: PreviewProvider {
    static var previews: some View {
        MoviesListScreenView()
    }
}
