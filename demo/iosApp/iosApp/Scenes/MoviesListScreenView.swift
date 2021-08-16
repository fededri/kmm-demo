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
    @State var state: MoviesState = MoviesState(movies: [], selectedMovie: nil)
    @State var showMovieDetail: Bool = false
    
    let viewModel = MoviesViewModel(threadInfo: ThreadInfoImpl())
    
    var body: some View {
        NavigationView {
            List() {
                ForEach(state.movies, id: \.self) { movie in
                    ZStack {
                        MovieView(movie: movie, onMovieClickListener: { selectedMovie in
                            viewModel.action(action: .SelectMovie(movie: selectedMovie))
                        })
                        if let selectedMovie = state.selectedMovie {
                            NavigationLink(destination: MovieDetailView(movie: selectedMovie), isActive: $showMovieDetail, label: {
                                EmptyView()
                            }).hidden()
                        }
                    }
                }
            }
            
            .navigationTitle("Movies")
            .toolbar(content: {
                HStack {
                    Button(action: {
                        viewModel.action(action: .RandomizeMoviesList())
                    }, label: {
                        Image(systemName: "shuffle")
                    })
                }
            })
        }.onAppear(perform: {
            DIContainer.shared.register(type: MoviesViewModel.self, component: viewModel)
            
            viewModel.observeState().collect { moviesState in
                guard let newState = moviesState else { return }
                self.state = newState
            } onCompletion: { error in
                print(error as Any)
            }
            
            viewModel.observeEvents().collect { event in
                guard let event = event else { return }
                processEvent(event: event)
            } onCompletion: { error in
                print(error as Any)
            }
            
        })
    }
    
    private func processEvent(event: MovieEvents) {
        switch event {
        case let event as MovieEvents.OpenSelectedMovie :
            self.showMovieDetail = true
        default:
            print("Event not recognized")
        }
    }
}

struct MoviesListScreenView_Previews: PreviewProvider {
    static var previews: some View {
        MoviesListScreenView()
    }
}
