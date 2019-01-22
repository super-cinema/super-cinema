//
//  Created by Krzysztof Pawski on 20/01/2019.
//  Copyright © 2019 SuperCinemaSpZoo. All rights reserved.
//

import Foundation

// MARK: - Api

struct Api {
    var getMovies = getMovies(completion:)
}

// MARK: - Movies

private func getMovies(completion: @escaping ResultBlock) {
    URLSession
        .shared
        .dataTask(with: urlRequest(), completionHandler: handler(with: completion))
        .resume()
}

// Helpers

fileprivate func urlRequest() -> URLRequest {
    let url = URL(string: "http://localhost:8080/movie")!
    return URLRequest(url: url)
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
