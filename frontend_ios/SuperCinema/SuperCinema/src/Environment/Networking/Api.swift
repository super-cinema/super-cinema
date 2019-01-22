//
//  Created by Krzysztof Pawski on 20/01/2019.
//  Copyright © 2019 SuperCinemaSpZoo. All rights reserved.
//

import Foundation

// MARK: - Api

struct Api {
    var getMovies = getMovies(completion:)
    var postMovie = postMovie(_:completion:)
}

// MARK: - Movies

private func getMovies(completion: @escaping ResultBlock) {
    URLSession
        .shared
        .dataTask(with: urlRequest(), completionHandler: handler(with: completion))
        .resume()
}

private func postMovie(_ movie: Movie, completion: @escaping ResultBlock) {
    let encoder = JSONEncoder()
    let body = try? encoder.encode(movie)
    let request = urlRequest(httpMethod: "POST", httpBody: body)

    URLSession
        .shared
        .dataTask(with: request, completionHandler: handler(with: completion))
        .resume()
}

// Helpers

fileprivate func urlRequest(httpMethod: String? = nil,
                            httpBody: Data? = nil) -> URLRequest {
    let url = URL(string: "http://localhost:8080/movie")!
    var request = URLRequest(url: url)
    request.httpMethod = httpMethod
    request.httpBody = httpBody
    request.setValue("Application/json", forHTTPHeaderField: "Content-Type")
    return request
}

fileprivate func handler(with completion: @escaping ResultBlock) -> (Data?, URLResponse?, Error?) -> Void {
        return { (data, response, error) in
            responseHandler(data: data,
                            response: response,
                            error: error,
                            completion: completion)
        }
}

fileprivate func responseHandler(data: Data?,
                                 response: URLResponse?,
                                 error: Error?,
                                 completion: ResultBlock) {
    if let error = error  {
        completion(.error(error))
        return
    }

    completion(.succes(data as Any))
}
