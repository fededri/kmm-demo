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
    
    var body: some View {
        VStack(alignment: .leading, spacing: 15) {
            Text(movie.name)
                .foregroundColor(.white)
                .fontWeight(.bold)
            Text("Random description")
                .foregroundColor(.white)
        }
        .padding(.horizontal, 4)
        .padding(.vertical, 4)
        .background(Color.red)
        .cornerRadius(6.0)
    }
}

struct MovieView_Previews: PreviewProvider {
    static var previews: some View {
        MovieView(movie: Movie(name: "Harry Potter"))
            .previewLayout(.sizeThatFits)
    }
}
