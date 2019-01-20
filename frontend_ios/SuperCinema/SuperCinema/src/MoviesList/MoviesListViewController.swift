//
//  FirstViewController.swift
//  SuperCinema
//
//  Created by Krzysztof Pawski on 20/01/2019.
//  Copyright © 2019 SuperCinemaSpZoo. All rights reserved.
//

import UIKit

final class MoviesListViewController: UIViewController {

    private var task: URLSessionTask?
    private var movies: [Movie] = []

    override func viewDidLoad() {
        super.viewDidLoad()

        title = "MoviesList"
    }

    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        loadMovies()
    }

    private func loadMovies() {
        let url = URL(string: "http://localhost:8080/movie")!
        task = URLSession.shared.dataTask(with: url,
                                          completionHandler: { [weak self] (data, response, error) in
                                            self?.handleMoviesResponse(data: data, response: response, error: error)
        })
        task?.resume()
    }

    private func handleMoviesResponse(data: Data?, response: URLResponse?, error: Error?) {
        if let data = data {
            let decoder = JSONDecoder()
            do {
                movies = try decoder.decode([Movie].self, from: data)
            } catch {
                print(error)
            }
        }
        if let error = error {
            print(error)
        }
    }
}

extension MoviesListViewController: UITableViewDataSource {

}

struct Movie: Codable {
    let id: Int
    let title: String
    let duration: Int
    let productionCountry: String?
    let productionYear: String?
    let directors: [Crew]
    let cast: [Crew]?
    let movieShow: String?
    let types: [String]
}

struct Crew: Codable {
}

