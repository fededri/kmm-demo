//
//  MovieDetailView.swift
//  iosApp
//
//  Created by Federico Torres on 28/07/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct MovieDetailView: View {
    
    let movie: Movie?
    var body: some View {
        VStack() {
            AsyncImage(
                url: URL(string: movie!.posterPath!)!,
                placeholder: {
                    Color.red.opacity(0.5)
                },
                image: {Image(uiImage: $0).resizable()}
                    
            )
            .frame(height: 300)
            .aspectRatio(contentMode: .fit)
        }.frame(minWidth: 0, maxWidth: .infinity, minHeight: 0, maxHeight: .infinity, alignment: .topLeading)
    }
}

struct MovieDetailView_Previews: PreviewProvider {
    static let movie = Movie(name: "Harry Potter", title: "Harry Potter and the prisoner of azkaban", popularity: 1200, video: false, voteAverage: 5, voteCount: 1230, overview: "Very good and nice film", posterPath: "https://lcnme.com/wp-content/uploads/2019/03/harry-potter-2-color.jpg")
    
    static var previews: some View {
        MovieDetailView(movie: movie)
    }
}
