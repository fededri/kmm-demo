//
//  MovieView.swift
//  iosApp
//
//  Created by Federico Torres on 09/07/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct MovieView: View {
    let movie: Movie
    var onMovieClickListener: ((Movie) -> (Void))? = nil
    
    var body: some View {
        HStack {
            AsyncImage(url: URL(string: movie.posterPath!)!, placeholder: {
                Text("Loading Image")
            },
            image: { Image(uiImage: $0).resizable()})
            .frame(width: 120, height: 120, alignment: .center)
            .clipShape(Circle())
            .scaledToFit()
            
            
            VStack(alignment: .leading, spacing: 15) {
                Text(movie.name)
                    .foregroundColor(.white)
                    .fontWeight(.bold)
                
                Text(movie.overview)
                    .lineLimit(/*@START_MENU_TOKEN@*/2/*@END_MENU_TOKEN@*/)
                    .foregroundColor(.white)
                
                HStack {
                    Text("Score:")
                        .padding(.horizontal, 2)
                        .foregroundColor(.white)
                    
                    Text(String(movie.voteAverage))
                        .foregroundColor(.white)
                    
                    Image(systemName: "star.fill")
                        .foregroundColor(.yellow)
                }
            }
        }
        .frame(maxWidth: .infinity)
        .padding(.horizontal, 2)
        .padding(.vertical, 8)
        .background(Color.red.opacity(0.5))
        .cornerRadius(6.0)
        .onTapGesture {
            guard let listener = onMovieClickListener else { return }
            listener(movie)
        }
        //: MAIN HSTACK
    }
    
}
    
    struct MovieView_Previews: PreviewProvider {
        static let movie = Movie(name: "Harry Potter", title: "Harry Potter and the prisoner of azkaban", popularity: 1200, video: false, voteAverage: 5, voteCount: 1230, overview: "Very good and nice film", posterPath: "https://lcnme.com/wp-content/uploads/2019/03/harry-potter-2-color.jpg")
        
        static var previews: some View {
            MovieView(movie: movie)
                .previewLayout(.sizeThatFits)
        }
    }
